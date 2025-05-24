package ar.com.grupoesfera.repartir.steps.grupos;
import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import io.cucumber.java.ast.Cuando;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class GruposSinMiembrosRepetidosSteps extends CucumberSteps {

    private List<String> groups = new ArrayList<>();

    @Cuando("el usuario crea un grupo con 2 miembros con nombre {string}")
    public void el_usuario_crea_un_grupo_con_miembros_con_nombre(String nombre) {
        var wait = new WebDriverWait(driver, 2);
        var createGroupButton = driver.findElement(By.id("crearGruposButton"));
        createGroupButton.click();
        driver.findElement(By.id("nombreGrupoNuevoInput")).sendKeys("Viaje con los pi!");
        var membersInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));
        membersInput.sendKeys(nombre);
        membersInput.sendKeys(Keys.ENTER);
        membersInput.sendKeys(nombre);
        membersInput.sendKeys(Keys.ENTER);
        driver.findElement(By.id("guardarGrupoNuevoButton")).click();
        wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
    }

    @Entonces("no deberia crear el grupo")
    public void no_deberia_crear_el_grupo() {
        var wait = new WebDriverWait(driver, 2);
        var mensajesToast = wait.withMessage("No salto el alert").until(visibilityOfElementLocated(By.id("mensajesToast")));
        wait.withMessage("El titulo de la alerta no es 'Error'").until(textToBePresentInElement(mensajesToast, "Error"));
        assertThat(mensajesToast.getText()).as("Texto del alert").contains("No se puede guardar");
    }

    @Cuando("el usuario crea un grupo con un miembro llamado {string} y 2 miembros llamados {string}")
    public void el_usuario_crea_un_grupo_con_un_miembros_llamado_y_2_miembros_llamados(String string, String string2) {
        var wait = new WebDriverWait(driver, 2);
        var createGroupButton = driver.findElement(By.id("crearGruposButton"));
        createGroupButton.click();
        driver.findElement(By.id("nombreGrupoNuevoInput")).sendKeys("Que rompimo?");
        var membersInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));
        membersInput.sendKeys(string);
        membersInput.sendKeys(Keys.ENTER);
        membersInput.sendKeys(string2);
        membersInput.sendKeys(Keys.ENTER);
        membersInput.sendKeys(string2);
        membersInput.sendKeys(Keys.ENTER);
        driver.findElement(By.id("guardarGrupoNuevoButton")).click();
        wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
    }

    @Cuando("el usuario crea dos grupos iguales con dos miembros llamados {string} y {string}")
    public void el_usuario_crea_dos_grupos_iguales_con_dos_miembros_llamados(String string, String string2) {
        for (int i = 0; i < 2; i++) {
            var wait = new WebDriverWait(driver, 2);
            var groupName = "Group N° " + i;
            groups.add(groupName);
            var createGroupButton = driver.findElement(By.id("crearGruposButton"));
            createGroupButton.click();
            driver.findElement(By.id("nombreGrupoNuevoInput")).sendKeys(groupName);
            var membersInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));
            membersInput.sendKeys(string);
            membersInput.sendKeys(Keys.ENTER);
            membersInput.sendKeys(string2);
            membersInput.sendKeys(Keys.ENTER);
            driver.findElement(By.id("guardarGrupoNuevoButton")).click();
            wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
        }
    }

    @Entonces("debería visualizar los dos grupos creados")
    public void deberiaVisualizarLosDosGruposCreados() {
        var rows = driver.findElements(By.cssSelector("app-grupos table tr"));
        assertThat(rows.size()).isGreaterThan(1);

        for (int i = 0; i < this.groups.size(); i++) {
            var cell = rows.get(i + 1).findElements(By.tagName("td"));
            var groupName = this.groups.get(i);
            var groupNameRow = cell.get(1).getText();
            assertThat(groupNameRow).isNotBlank();
            assertThat(groupNameRow).isEqualTo(groupName);
        }
    }
}
