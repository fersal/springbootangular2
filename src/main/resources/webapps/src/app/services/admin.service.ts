import {Injectable} from '@angular/core';
import {Http, Headers, Response} from "@angular/http";
import {Observable} from "rxjs";
import {User} from "../domain/user";
import {isNullOrUndefined} from "util";
import {UserRole} from "../domain/userRole";

@Injectable()
export class AdminService {
    private headers = new Headers({'Content-Type': 'application/json'});
    private baseUrl = 'http://localhost:8080/';
    private searchedUser: User;

    constructor(private http: Http) {

    }

    updatePassword(userId: number, password: string): Observable<boolean> {
        let pwdUrl = this.baseUrl + "users/pwd";
        let user = new User();
        user.userId = userId;
        user.password = password;
        return this.http.post(pwdUrl, JSON.stringify(user), {headers: this.headers})
            .map((savedUser: Response) => {
                let user = savedUser.json() as User;
                return isNullOrUndefined(user.firstName);
            })
            .catch(this.handleError);
    }

    updateName(userId: number, firstName: string, lastName: string): Observable<boolean> {
        let nameUrl = this.baseUrl + "users/name";
        let newUser = new User();
        newUser.userId = userId;
        newUser.firstName = firstName;
        newUser.lastName = lastName;
        return this.http.post(nameUrl, JSON.stringify(newUser), {headers: this.headers})
            .map((savedUser: Response) => {
                let user = savedUser.json() as User;
                return isNullOrUndefined(user.password);
            })
            .catch(this.handleError);
    }

    fetchUser(username: string): Observable<User> {
        let findUserUrl = this.baseUrl + "users/find";
        let user = new User();
        user.username = username;
        return this.http.post(findUserUrl, JSON.stringify(user), {headers: this.headers})
            .map((foundUser: Response) => foundUser.json() as User)
            .catch(this.handleError);
    }

    updateRole(newRole: UserRole): Observable<boolean> {
        let roleUrl = this.baseUrl + "users/updateRole";
        return this.http.post(roleUrl, JSON.stringify(newRole), {headers: this.headers})
            .map((savedRole: Response) => {
                let role = savedRole.json() as UserRole;
                return isNullOrUndefined(role.userRoleId);
            })
            .catch(this.handleError);
    }

    fetchRole(username: string): Observable<UserRole> {
        let roleUrl = this.baseUrl + "users/getRole";
        let user = new User();
        user.username = username;
        return this.http.post(roleUrl, JSON.stringify(user), {headers: this.headers})
            .map((userRole: Response) => {

            userRole.json() as UserRole})
            .catch(this.handleError);
    }

    handleError(error: any): Observable<any> {
        console.error("Error in AdminService:: ", error);
        return Observable.throw(error.json().error || "Server error on AdminSerivce");
    }
 }