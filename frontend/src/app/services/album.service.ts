import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {Album} from "../models/Album";
import {apiUrl} from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class AlbumService {

    private searchUrl = `${apiUrl}/search`;

    constructor(private http: HttpClient) {
    }

    search(keyword): Observable<any> {
        const headers = new HttpHeaders()
        .set('content-type', 'application/json')
        .set('Access-Control-Allow-Origin', '*')
        .set('Access-Control-Allow-Methods', 'GET, POST, PATCH, PUT, DELETE, OPTIONS');

        return this.http.get(`${this.searchUrl}?query=${keyword}`,{ 'headers': headers }).pipe();
    }

}
