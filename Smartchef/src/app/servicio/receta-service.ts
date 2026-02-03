import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RecetaCard } from '../modelos/receta-card.model';
import { RecetaDetalle } from '../modelos/receta-detalle.model';
@Injectable({ providedIn: 'root' })
export class RecetaService {

  private apiUrl = 'https://smartchef-1-sdnt.onrender.com/api/recetas';

  constructor(private http: HttpClient) {}
  eliminarReceta(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

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
  actualizarReceta(id: number, dto: any) {
    return this.http.put(`${this.apiUrl}/${id}`, dto);
  }

  marcarFavorito(idReceta: number, idUsuario: number) {
    return this.http.post(
      `${this.apiUrl}/${idReceta}/favorito`,
      null,
      { params: { idUsuario } }
    );
  }
  obtenerDetalleReceta(id: number) {
    return this.http.get<RecetaDetalle>(
      `http://localhost:8080/api/recetas/${id}`
    );
  }
  crearReceta(dto: any) {
    return this.http.post(this.apiUrl, dto);
  }

}
