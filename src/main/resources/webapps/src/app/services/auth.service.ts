import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {CanActivate, Router, RouterStateSnapshot, ActivatedRouteSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {User} from "../domain/user";
import {Journal} from "../domain/journal";

@Injectable()
export class AuthService implements CanActivate {
    private headers = new Headers({'Content-Type': 'application/json'});
    private baseUrl = 'http://localhost:8080/';
    public isLoggedIn = false;
    public targetUrl: string;
    private journal: Journal;

    constructor(private http: Http, private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        this.targetUrl = state.url;
        return this.checkLoginStatus();
    }

    checkLoginStatus(): boolean {
        if (this.isLoggedIn) {
            return true;
        }
        this.router.navigate(['/login']);
        return false;
    }

    authenticate(user: User): Observable<Journal> {
        let authUrl = this.baseUrl + "users/authenticate";

        return this.http.post(authUrl, JSON.stringify(user), {headers: this.headers})
            .map((userJournal: Response) => this.handleAuthenticationResponse(userJournal.json() as Journal))
            .catch(this.handleError);
    }

    private handleAuthenticationResponse(journal: Journal): Journal {
        this.journal = journal;
        return this.journal;
    }

    private handleError(error: any): Observable<any> {
        console.error("Error occurred on AuthService:::", error);
        return Observable.throw(error.json().error || 'Server error');
    }

    signOff(): void {
        this.isLoggedIn = false;
        this.journal = null;
        this.router.navigate(['/login']);
    }

    getCurrentJournal(): Journal {
        return this.journal;
    }

    getCurrentUserId(): number {
        return this.journal.user.userId;
    }
}