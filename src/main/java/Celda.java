public class Celda {

    // region atributos
    private Articulo[] articulos;
    // endregion


    public Celda() {
        articulos = new Articulo[Ctes.LIM_ARTICULOS];
    }

    // region getter y setter
    public int getCapacidadArticulos() {
        return articulos.length;
    }

    /**
     * Obtiene el artículo de la celda correspondiente a su número
     * @param numeroArticulo número del 1 a la capacidad de la celda
     *                       se supone comprobado antes, no se valida
     * @return referencia al artículo correspondiente, puede ser null
     */
    public Articulo getArticulo(int numeroArticulo) {
        return articulos[numeroArticulo-1];
    }

    public int getCantidadArticulos() {
        int n = 0;
        for (int i = 0; i < articulos.length; i++) {
            if (articulos[i] != null) { n++; }
        }
        return n;
    }
    // endregion

    // region métodos privados

    /**
     * Si el id del artículo de una posición coincide
     * @param id identificador del artículo
     * @param numeroArticulo posición en la celda del 1 a la capacidad de la celda
     *                       no se verifica el rango
     * @return verdadero si coincide
     */
    private boolean esArticulo(String id, int numeroArticulo) {
        Articulo articulo = getArticulo(numeroArticulo);
        return articulo != null && articulo.getId().equals(id.toUpperCase());
    }

    /**
     * Si la posición no tiene una referencia a un artículo
     * @param numeroArticulo valor del 1 a la capacidad de la celda
     *                       no se verifica el rango
     * @return verdadero si no hay una referencia a un objeto (es decir, es null)
     */
    private boolean estaVacio(int numeroArticulo) {
        return getArticulo(numeroArticulo) == null;
    }
    // endregion

    // region CRUD

    public boolean haySitio() {
        return getCantidadArticulos() < getCapacidadArticulos();
    }

    public boolean estaArticulo(String id) {
        for (int i = 1; i <= getCapacidadArticulos(); i++) {
            if (esArticulo(id, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean insertarArticulo(Articulo articulo) {
        for (int i = 1; i <= getCapacidadArticulos(); i++) {
            if (estaVacio(i)) {
                articulos[i-1] = articulo;
                return true;
            }
        }
        return false;
    }

    public boolean sacarArticulo(String id) {
        for (int i = 1; i <= getCapacidadArticulos(); i++) {
            if (esArticulo(id, i)) {
                articulos[i-1] = null;
                return true;
            }
        }
        return false;
    }
    // endregion

    public void pintar() {
        System.out.println("-".repeat(14));
        for (int i = 1; i <= getCapacidadArticulos(); i++) {
            Articulo articulo = getArticulo(i);
            String s = articulo != null?articulo.getId():" ".repeat(10);
            System.out.printf("| %10s |\n", s);
        }
        System.out.println("-".repeat(14));
    }

    public double getValorArticulos() {
        double valor = 0;
        for (int i = 0; i < articulos.length; i++) {
            if (articulos[i]!=null) {
                valor += articulos[i].getPrecio();
            }
        }
        return valor;
    }

}
