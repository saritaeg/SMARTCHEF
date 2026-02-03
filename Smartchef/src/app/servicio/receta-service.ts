import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RecetaCard } from '../modelos/receta-card.model';
import { RecetaDetalle } from '../modelos/receta-detalle.model';

@Injectable({ providedIn: 'root' })
export class RecetaService {

  /**
   * ⚠️ RECUERDA:
   * 1. Mientras pruebes en tu PC: usa 'http://localhost:8080/api/recetas'
   * 2. Para que tus compañeros lo vean: debes subir el Java a Render y poner
   * aquí la URL que te dé Render (ej: 'https://smartchef-backend.onrender.com/api/recetas')
   */
  private apiUrl = 'https://smartchef-backend.onrender.com/api/recetas';

  constructor(private http: HttpClient) {}

  obtenerRecetas(
    ingrediente?: string,
    vegetariano?: boolean,
    sinGluten?: boolean,
    rapido?: boolean,
    economico?: boolean
  ): Observable<RecetaCard[]> {
    let params = new HttpParams();
    if (ingrediente) params = params.set('ingrediente', ingrediente);
    if (vegetariano) params = params.set('vegetariano', vegetariano);
    if (sinGluten) params = params.set('sinGluten', sinGluten);
    if (rapido) params = params.set('rapido', rapido);
    if (economico) params = params.set('economico', economico);

    return this.http.get<RecetaCard[]>(this.apiUrl, { params });
  }

  obtenerDetalleReceta(id: number): Observable<RecetaDetalle> {
    return this.http.get<RecetaDetalle>(`${this.apiUrl}/${id}`);
  }

  crearReceta(dto: any): Observable<any> {
    return this.http.post(this.apiUrl, dto);
  }

  actualizarReceta(id: number, dto: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, dto);
  }

  eliminarReceta(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  marcarFavorito(idReceta: number, idUsuario: number): Observable<any> {
    return this.http.post(
      `${this.apiUrl}/${idReceta}/favorito`,
      null,
      { params: { idUsuario: idUsuario.toString() } }
    );
  }

  quitarFavorito(idReceta: number, idUsuario: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${idReceta}/favorito`, {
      params: { idUsuario: idUsuario.toString() }
    });
  }

  obtenerFavoritos(idUsuario: number): Observable<RecetaCard[]> {
    return this.http.get<RecetaCard[]>(`${this.apiUrl}/favoritos`, {
      params: { idUsuario: idUsuario.toString() }
    });
  }
}
