package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import io.cucumber.java.ast.Cuando;
import io.cucumber.java.es.Entonces;

public class GruposSinMiembrosRepetidosSteps extends CucumberSteps {

    @Cuando("el usuario crea un grupo con 2 miembros con nombre {string}")
    public void el_usuario_crea_un_grupo_con_miembros_con_nombre(String nombre) {
        throw new io.cucumber.java.PendingException();
    }

    @Entonces("no deberia crear el grupo")
    public void no_deberia_crear_el_grupo() {
        throw new io.cucumber.java.PendingException();
    }

    @Cuando("el usuario crea un grupo con un miembro llamado {string} y 2 miembros llamados {string}")
    public void el_usuario_crea_un_grupo_con_un_miembros_llamado_y_2_miembros_llamados(String string, String string2) {
        throw new io.cucumber.java.PendingException();
    }

    @Cuando("el usuario crea dos grupos iguales con dos miembros llamados {string} y {string}")
    public void el_usuario_crea_dos_grupos_iguales_con_dos_miembros_llamados(String string, String string2) {
        throw new io.cucumber.java.PendingException();
    }

    @Entonces("debería visualizar los dos grupos creados")
    public void debería_visualizar_los_dos_grupos_creados() {
        throw new io.cucumber.java.PendingException();
    }
}
