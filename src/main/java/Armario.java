/**
 * Clase que representa el armario mediante
 * una matriz de celdas.
 * @version 2.1
 */
public class Armario {

    // region atributos
    private Celda[][] celdas;
    // endregion

    // region constructores
    public Armario(int filas, int columnas) {
        celdas = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }
    // endregion

    // region getter y setter
    public int getCantidadArticulos() {
        int n = 0;
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[0].length; j++) {
                n += celdas[i][j].getCantidadArticulos();
            }            
        }
        return n;
    }

    public int getCapacidadArticulos() {
        return celdas.length * celdas[0].length * celdas[0][0].getCapacidadArticulos();
    }
    // endregion

    // region CRUD
    public Posicion estaArticulo(String id) {
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[0].length; j++) {
                if (celdas[i][j].estaArticulo(id)) { return new Posicion(i+1,j+1); }
            }
        }
        return null;
    }

    /**
     * Inserta un artículo en la primera posición libre de una celda.
     * Consulta si hay sitio en las celdas buscando sitio de la primera fila a la última.
     * Dentro de cada fila busca desde la primera columna a la última.
     * @param articulo referencia al artículo
     * @return devuelve la posición en la que se ha insertado o null si el armario está lleno.
     *         La posición considera que la primera fila y columna empiezan en 1.
     */
    public Posicion insertarArticulo(Articulo articulo) {
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[0].length; j++) {
                if (celdas[i][j].haySitio()) {
                    celdas[i][j].insertarArticulo(articulo);
                    return new Posicion(i+1, j+1);
                }
            }            
        }
        return null;
    }

    public boolean insertarArticulo(Articulo articulo, Posicion posicion) {
        if (!celdas[posicion.getNumeroFila()-1][posicion.getNumeroColumna()-1].haySitio()) { return false; }
        celdas[posicion.getNumeroFila()-1][posicion.getNumeroColumna()-1].insertarArticulo(articulo);
        return true;
    }

    public Posicion sacarArticulo(String id) {
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[0].length; j++) {
                if (celdas[i][j].estaArticulo(id)) {
                    celdas[i][j].sacarArticulo(id);
                    return new Posicion(i+1, j+1);
                }
            }
        }
        return null;
    }
    // endregion

    public void pintar() {
        int limFilas = celdas.length;
        int limColumnas = celdas[0].length;
        int limArticulos = celdas[0][0].getCapacidadArticulos();
        System.out.println("#".repeat(1 + 13 * limColumnas));
        for (int f = 0; f < limFilas; f++) {
            System.out.println("-".repeat(1 + 13 * limColumnas));
            for (int a = 0; a < limArticulos; a++) {
                System.out.print("|");
                for (int c = 0; c < limColumnas; c++) {
                    Celda celda = celdas[f][c];
                    Articulo articulo = celda.getArticulo(a+1);
                    String s = articulo != null?articulo.getId():" ".repeat(10);
                    System.out.printf(" %10s |", s);
                }
                System.out.println();
            }
            System.out.println("-".repeat(1 + 13 * limColumnas));
        }
        System.out.println("#".repeat(1 + 13 * limColumnas));
    }

    public double getValorArticulos() {
        double valor = 0;
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[0].length; j++) {
                valor += celdas[i][j].getValorArticulos();
            }
        }
        return valor;
    }

}
