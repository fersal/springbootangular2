import {Component, OnInit} from "@angular/core";
import {User} from "./domain/user";
import {AuthService} from "./services/auth.service";
import {UserRole} from "./domain/userRole";
import {AdminService} from "./services/admin.service";
import {Observable} from "rxjs";
import {isNullOrUndefined} from "util";

@Component({
    selector: 'admin',
    templateUrl: 'admin.html'
})

export class AdminComponent implements OnInit{

    private userNameFound: string;
    private userRoleFound: UserRole;
    private usernameToSearch: string;
    private isAdmin: boolean;
    private newPassword: string;
    private confirmPassword: string;
    private firstName: string;
    private lastName: string;
    private currentUser: User;
    public currentRole: UserRole;

    constructor(private authService: AuthService, private adminService: AdminService) {
        this.currentRole = new UserRole();
    }

    ngOnInit(): void {
        this.currentUser = this.authService.getCurrentJournal().user;
        this.firstName = this.currentUser.firstName;
        this.lastName = this.currentUser.lastName;

        this.adminService.fetchRole(this.currentUser.username)
            .subscribe(fetchedRole => {this.currentRole = fetchedRole}
                , error => {console.error("Error while fetching UserRole for Admin page to be displayed: " + error)});
        this.showAdminArea();

        console.info("Current user: " + this.currentUser.firstName);
        console.info("Current userRole: " + this.currentRole.role + " for " + this.currentRole.userName);
    }

    private showAdminArea() {
        this.isAdmin = !isNullOrUndefined(this.currentRole.userRoleId) && this.currentRole.role !== "ROLE_USER";
    }

    updatePassword(): void {
        if (!this.newPassword || !this.confirmPassword || (this.newPassword !== this.confirmPassword)) {
            alert("New password is empty or does not match. Try again.");
            return;
        }

        let result:Observable<boolean> =
            this.adminService.updatePassword(this.authService.getCurrentUserId(), this.newPassword);
        if (result) {
            alert("Password successfully updated!")
        } else {
            alert("There was a problem updating your password. Please try again.")
        }
    }

    updateName(): void {
        if (!this.firstName || !this.lastName) {
            alert("Fields can not be empty. Try again.")
            return;
        }

        let result: Observable<boolean> =
            this.adminService.updateName(this.authService.getCurrentUserId(), this.firstName, this.lastName);
        if (result) {
            alert("Name successfully updated.");
        } else {
            alert("There was a problem updating Name, try again.")
        }
    }

    searchUser(): void {
        if (!this.usernameToSearch) {
            alert("Username can not be empty.");
            return;
        }

        // this.userNameFound = "";
        this.adminService.fetchUser(this.usernameToSearch).subscribe(user => {this.userNameFound = user.username});
        if (this.userNameFound === "" || isNullOrUndefined(this.userNameFound)) {
            alert("Username \"" + this.usernameToSearch + "\" not found.");
        }

        this.userRoleFound = new UserRole();
        this.adminService.fetchRole(this.userNameFound).subscribe(userRole => {this.userRoleFound = userRole});
        if (isNullOrUndefined(this.userRoleFound.userRoleId)) {
            alert("Could not find Role for username \"" + this.usernameToSearch + "\"");
        }

        console.info("Search found user: " + this.userNameFound);
        console.info("User role found: " + this.userRoleFound.role + " for username " + this.userRoleFound.userName);
        //ToDo: need to set the correct userRole radio button
    }

    updateRole(): void {

    }
}
