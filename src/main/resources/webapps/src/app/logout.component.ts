import {Component} from "@angular/core";
import {AuthService} from "./services/auth.service";

@Component({
    selector: 'logout',
    templateUrl: 'logout.html'
})

export class LogoutComponent {

    constructor(private authService: AuthService) {
        console.info("Successfully signed off.");
        this.authService.signOff();
    }
}