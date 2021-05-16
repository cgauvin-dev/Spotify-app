import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavigationComponent} from './parts/navigation/navigation.component';
import {AppRoutingModule} from './app-routing.module';
import {SearchComponent} from './pages/search/search.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {CookieService} from "ngx-cookie-service";

@NgModule({
    declarations: [
        AppComponent,
        NavigationComponent,
        SearchComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,

    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
