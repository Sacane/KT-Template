import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormJoin} from "../model/form-join.model";
import {Observable} from "rxjs";
import {routes} from "../routes";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FormJoinService {

  httpClient = inject(HttpClient)
  url = environment.apiUrl

  joinForm(form: FormJoin): Observable<FormJoin> {
    return this.httpClient.post<FormJoin>(`${this.url}${routes.join}`, form);
  }
}
