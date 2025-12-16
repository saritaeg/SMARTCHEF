import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private API_URL = 'http://localhost:8080';
  private http = inject(HttpClient);

  login(email: string, contrasena: string): Observable<any> {
    const body = {
      email: email.trim().toLowerCase(),
      contrasenia: contrasena.trim()
    };
    console.log('Enviando login:', body);
    return this.http.post(`${this.API_URL}/usuarios/login`, body).pipe(
      tap({
        next: (res) => console.log('Respuesta backend:', res),
        error: (err) => console.error('Error backend:', err)
      })
    );
  }

  crearUsuario(usuario: any): Observable<any> {
    return this.http.post(`${this.API_URL}/usuarios/crear`, usuario);
  }
}
