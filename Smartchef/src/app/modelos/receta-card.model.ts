export interface RecetaCard {
  idReceta: number;
  nombre: string;
  descripcion?: string;
  tiempoPreparacion: number;
  vegetariano: boolean;
  sinGluten: boolean;
  rapido: boolean;
  economico: boolean;
  fotoUrl: string;
  favorito?: boolean;
}
