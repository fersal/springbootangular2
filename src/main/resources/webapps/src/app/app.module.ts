import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {LoginComponent} from './login.component';
import {EntryComponent} from './entry.component';
import {ReportsComponent} from './reports.component';
import {AdminComponent} from './admin.component';
import {MainService} from './services/main.service';
import {JournalsComponent} from './journals.component';
import {JournalEntriesComponent} from './journalEntries.component';
import {LogoutComponent} from './logout.component'
import {AuthService} from './services/auth.service';
import {AdminService} from "./services/admin.service";

const appRoutes: Routes = [
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: 'login', component: LoginComponent},
    {path: 'entry/:journalId', component: EntryComponent, canActivate: [AuthService]},
    {path: 'journal/:journalId', component: EntryComponent, canActivate: [AuthService]},
    {path: 'admin', component: AdminComponent, canActivate: [AuthService]},
    {path: 'logout', component: LogoutComponent}
];

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        EntryComponent,
        ReportsComponent,
        AdminComponent,
        JournalsComponent,
        JournalEntriesComponent,
        LogoutComponent

    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        RouterModule.forRoot(appRoutes)
    ],
    providers: [MainService, AuthService, AdminService],
    bootstrap: [AppComponent]
})

export class AppModule {
}
