package nrg.inc.synhub

import android.os.Build
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.synhub.tasks.views.CreateTask
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test for Create Task screen using Compose UI Test.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CreateTaskTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun createTask_displaysAllUIElements() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Verify all text fields are displayed
            composeTestRule.onNodeWithText("Titulo de la tarea").assertIsDisplayed()
            composeTestRule.onNodeWithText("Descripción de la tarea").assertIsDisplayed()
            composeTestRule.onNodeWithText("Integrante").assertIsDisplayed()
            composeTestRule.onNodeWithText("Fecha de entrega").assertIsDisplayed()

            // Verify the save and cancel buttons are displayed
            composeTestRule.onNodeWithText("Guardar").assertIsDisplayed()
            composeTestRule.onNodeWithText("Cancelar").assertIsDisplayed()
        }
    }

    @Test
    fun createTask_canEnterTaskTitle() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Find the title text field and enter text
            composeTestRule.onNode(
                hasText("Titulo de la tarea") and hasSetTextAction()
            ).performTextInput("Mi Tarea de Prueba")

            // Verify the text was entered
            composeTestRule.onNodeWithText("Mi Tarea de Prueba").assertIsDisplayed()
        }
    }

    @Test
    fun createTask_canEnterTaskDescription() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Find the description text field and enter text
            composeTestRule.onNode(
                hasText("Descripción de la tarea") and hasSetTextAction()
            ).performTextInput("Esta es una descripción de prueba para la tarea")

            // Verify the text was entered
            composeTestRule.onNodeWithText("Esta es una descripción de prueba para la tarea")
                .assertIsDisplayed()
        }
    }

    @Test
    fun createTask_canFillTitleAndDescription() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Fill title field
            composeTestRule.onNode(
                hasText("Titulo de la tarea") and hasSetTextAction()
            ).performTextInput("Implementar nueva funcionalidad")

            // Fill description field
            composeTestRule.onNode(
                hasText("Descripción de la tarea") and hasSetTextAction()
            ).performTextInput("Desarrollar el módulo de autenticación con OAuth2")

            // Verify all texts are displayed
            composeTestRule.onNodeWithText("Implementar nueva funcionalidad").assertIsDisplayed()
            composeTestRule.onNodeWithText("Desarrollar el módulo de autenticación con OAuth2")
                .assertIsDisplayed()
        }
    }

    @Test
    fun createTask_saveButtonIsClickable() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Verify the save button exists and is clickable
            composeTestRule.onNode(
                hasText("Guardar") and hasClickAction()
            ).assertIsEnabled()
        }
    }

    @Test
    fun createTask_cancelButtonIsClickable() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Verify the cancel button exists and is clickable
            composeTestRule.onNode(
                hasText("Cancelar") and hasClickAction()
            ).assertIsEnabled()
        }
    }

    @Test
    fun createTask_textFieldsAcceptInput() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Test that all text fields can receive input
            val titleField = composeTestRule.onNode(
                hasText("Titulo de la tarea") and hasSetTextAction()
            )
            titleField.assertExists()
            titleField.performTextInput("Test Title")

            val descriptionField = composeTestRule.onNode(
                hasText("Descripción de la tarea") and hasSetTextAction()
            )
            descriptionField.assertExists()
            descriptionField.performTextInput("Test Description")
        }
    }

    @Test
    fun createTask_canClearAndReenterText() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Enter text in title field
            val titleField = composeTestRule.onNode(
                hasText("Titulo de la tarea") and hasSetTextAction()
            )
            titleField.performTextInput("Primer Titulo")

            // Clear and enter new text
            titleField.performTextClearance()
            titleField.performTextInput("Segundo Titulo")

            // Verify the new text is displayed
            composeTestRule.onNodeWithText("Segundo Titulo").assertIsDisplayed()
            composeTestRule.onNodeWithText("Primer Titulo").assertDoesNotExist()
        }
    }

    @Test
    fun createTask_topBarDisplaysTitle() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Verify the top bar displays "Crear Tarea"
            composeTestRule.onNodeWithText("Crear Tarea").assertIsDisplayed()
        }
    }

    @Test
    fun createTask_memberSelectorExists() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Verify the member selector field exists
            composeTestRule.onNode(
                hasText("Integrante")
            ).assertExists()
        }
    }

    @Test
    fun createTask_dateSelectorExists() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Verify the date selector field exists
            composeTestRule.onNode(
                hasText("Fecha de entrega")
            ).assertExists()
        }
    }

    @Test
    fun createTask_allFieldsExistAndAreInteractable() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            // Verify all fields exist
            composeTestRule.onNodeWithText("Titulo de la tarea").assertExists()
            composeTestRule.onNodeWithText("Descripción de la tarea").assertExists()
            composeTestRule.onNodeWithText("Integrante").assertExists()
            composeTestRule.onNodeWithText("Fecha de entrega").assertExists()

            // Verify buttons exist
            composeTestRule.onNodeWithText("Guardar").assertExists()
            composeTestRule.onNodeWithText("Cancelar").assertExists()
        }
    }

    @Test
    fun createTask_canEnterLongDescription() {
        // Set up the UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            composeTestRule.setContent {
                val navController = rememberNavController()
                CreateTask(nav = navController)
            }

            val longDescription = "Esta es una descripción muy larga que debería ser manejada " +
                    "correctamente por el campo de texto. El campo debe permitir múltiples líneas " +
                    "y mostrar todo el contenido sin problemas."

            // Enter long text in description field
            composeTestRule.onNode(
                hasText("Descripción de la tarea") and hasSetTextAction()
            ).performTextInput(longDescription)

            // Verify the text was entered (check for a substring to ensure it exists)
            composeTestRule.onNode(
                hasText(longDescription, substring = true)
            ).assertExists()
        }
    }
}