import {Component, Output, EventEmitter, Input} from "@angular/core";
import {User} from "./domain/user";
import {MainService} from "./services/main.service";
import {isNullOrUndefined} from "util";
import {Router, NavigationExtras} from "@angular/router";
import {Journal} from "./domain/journal";
import {AuthService} from "./services/auth.service";

@Component({
    selector: 'app-root',
    templateUrl: './login.html',
})

export class LoginComponent {
    private journal: Journal;
    private newUser: boolean;
    private loginUser: User;

    constructor(private mainService: MainService, private authService: AuthService, private router: Router) {
        this.newUser = false;
        this.loginUser = new User();
    }

    onSignUp(): void {
        this.newUser = true;
    }

    onSignIn(): void {
        if (!this.loginUser || !this.loginUser.username || !this.loginUser.password) {
            alert("Fields can not be empty. Try again.")
            return;
        }
        console.log("attempting to login with " + this.loginUser.username);

        this.authService.authenticate(this.loginUser)
            .subscribe(userJournal => {
                this.journal = userJournal;
                if (!isNullOrUndefined(this.journal)) {
                    this.authService.isLoggedIn = true;
                    this.router.navigate(['/journal/' + this.journal.journalId]);
                } else {
                    alert("Unable to login. Check username/password.");
                }
            }, error => {
                console.error("Authentication error: " + error);
            });
    }

    createNewUser(): void {
        //ToDo: need input validation here, no empties, etc.
        let userCreated: Promise<User> = this.mainService.createUser(this.loginUser);
        userCreated.then(user => this.loginUser = user);
        if (!isNullOrUndefined(this.loginUser.userId)) {
            console.log("Created username " + this.loginUser.username);
            this.newUser = false;
            return;
        }
        console.error("There was a problem while creating user: " + this.loginUser);
    }
}
