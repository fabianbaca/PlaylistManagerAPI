import { Song } from "./Song"; 

export class PlayList {
  nombre: string;           
  descripcion: string;     
  canciones: Song[]; 

  constructor(nombre: string, descripcion: string, canciones: Song[]) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.canciones = canciones;
  }
}
