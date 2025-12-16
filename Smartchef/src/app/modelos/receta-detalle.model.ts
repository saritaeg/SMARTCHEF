export interface IngredienteCantidad {
  nombre: string;
  cantidad: number;
  unidad: string;
  categoria: string;
}

export interface Instruccion {
  pasoNumero: number;
  descripcion: string;
  imagenUrl?: string;
}

export interface RecetaDetalle {
  idReceta: number;
  nombre: string;
  descripcion: string;
  tiempoPreparacion: number;
  vegetariano: boolean;
  sinGluten: boolean;
  rapido: boolean;
  economico: boolean;
  fotoUrl: string;
  idUsuario: number;
  ingredientes: IngredienteCantidad[];
  instrucciones: Instruccion[];
}
