export class Song {
    titulo: string;       
    artista: string;      
    album: string;       
    anno: string;        
    genero: string;      
  
    constructor(titulo: string, artista: string, album: string, anno: string, genero: string) {
      this.titulo = titulo;
      this.artista = artista;
      this.album = album;
      this.anno = anno;
      this.genero = genero;
    }
}