package nrg.inc.synhub

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.synhub.groups.views.CreateGroup
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test for Create Group screen using Compose UI Test.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CreateGroupTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun createGroup_displaysAllUIElements() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Verify all text fields are displayed
        composeTestRule.onNodeWithText("Nombre de grupo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Descripción del grupo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Url de la foto de perfil").assertIsDisplayed()

        // Verify the create button is displayed (using testTag or filtering by button role)
        composeTestRule.onAllNodesWithText("Crear Grupo").assertCountEquals(2)

        // Verify there's a clickable button with "Crear Grupo" text
        composeTestRule.onNode(
            hasText("Crear Grupo") and hasClickAction() and !hasSetTextAction()
        ).assertIsDisplayed()
    }

    @Test
    fun createGroup_canEnterGroupName() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Find the group name text field and enter text
        composeTestRule.onNode(
            hasText("Nombre de grupo") and hasSetTextAction()
        ).performTextInput("Mi Grupo de Prueba")

        // Verify the text was entered
        composeTestRule.onNodeWithText("Mi Grupo de Prueba").assertIsDisplayed()
    }

    @Test
    fun createGroup_canEnterGroupDescription() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Find the description text field and enter text
        composeTestRule.onNode(
            hasText("Descripción del grupo") and hasSetTextAction()
        ).performTextInput("Esta es una descripción de prueba para el grupo")

        // Verify the text was entered
        composeTestRule.onNodeWithText("Esta es una descripción de prueba para el grupo")
            .assertIsDisplayed()
    }

    @Test
    fun createGroup_canEnterProfilePictureUrl() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Find the URL text field and enter text
        composeTestRule.onNode(
            hasText("Url de la foto de perfil") and hasSetTextAction()
        ).performTextInput("https://example.com/image.jpg")

        // Verify the text was entered
        composeTestRule.onNodeWithText("https://example.com/image.jpg")
            .assertIsDisplayed()
    }

    @Test
    fun createGroup_canFillAllFieldsAndClickButton() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Fill all fields
        composeTestRule.onNode(
            hasText("Nombre de grupo") and hasSetTextAction()
        ).performTextInput("Grupo de Desarrollo")

        composeTestRule.onNode(
            hasText("Descripción del grupo") and hasSetTextAction()
        ).performTextInput("Grupo para desarrollo de aplicaciones móviles")

        composeTestRule.onNode(
            hasText("Url de la foto de perfil") and hasSetTextAction()
        ).performTextInput("https://example.com/grupo.png")

        // Verify all texts are displayed
        composeTestRule.onNodeWithText("Grupo de Desarrollo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Grupo para desarrollo de aplicaciones móviles")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("https://example.com/grupo.png").assertIsDisplayed()

        // Click the create button (button with text "Crear Grupo" that is NOT a text field)
        composeTestRule.onNode(
            hasText("Crear Grupo") and hasClickAction() and !hasSetTextAction()
        ).performClick()
    }

    @Test
    fun createGroup_buttonIsClickable() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Verify the button exists and is clickable (not a text field)
        composeTestRule.onNode(
            hasText("Crear Grupo") and hasClickAction() and !hasSetTextAction()
        ).assertIsEnabled()
    }

    @Test
    fun createGroup_textFieldsAcceptInput() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Test that all text fields can receive input
        val groupNameField = composeTestRule.onNode(
            hasText("Nombre de grupo") and hasSetTextAction()
        )
        groupNameField.assertExists()
        groupNameField.performTextInput("Test")

        val descriptionField = composeTestRule.onNode(
            hasText("Descripción del grupo") and hasSetTextAction()
        )
        descriptionField.assertExists()
        descriptionField.performTextInput("Test Description")

        val urlField = composeTestRule.onNode(
            hasText("Url de la foto de perfil") and hasSetTextAction()
        )
        urlField.assertExists()
        urlField.performTextInput("https://test.com")
    }

    @Test
    fun createGroup_canClearAndReenterText() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Enter text in group name field
        val groupNameField = composeTestRule.onNode(
            hasText("Nombre de grupo") and hasSetTextAction()
        )
        groupNameField.performTextInput("Primer Nombre")

        // Clear and enter new text
        groupNameField.performTextClearance()
        groupNameField.performTextInput("Segundo Nombre")

        // Verify the new text is displayed
        composeTestRule.onNodeWithText("Segundo Nombre").assertIsDisplayed()
        composeTestRule.onNodeWithText("Primer Nombre").assertDoesNotExist()
    }

    @Test
    fun createGroup_topBarBackButtonExists() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // The back button is an IconButton with ArrowBack icon
        // Since text fields are also clickable, we need to filter them out
        // by checking that it's NOT a text field (doesn't have SetTextAction)
        composeTestRule.onAllNodes(
            hasClickAction() and !hasSetTextAction()
        ).assertCountEquals(2) // Should have 2: back button + "Crear Grupo" button
    }

    @Test
    fun createGroup_verifyLayoutStructure() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Verify the screen has the correct number of text fields (3)
        composeTestRule.onAllNodes(hasSetTextAction()).assertCountEquals(3)

        // Verify there's exactly one button with "Crear Grupo" text (excluding the title)
        composeTestRule.onAllNodes(
            hasText("Crear Grupo") and hasClickAction() and !hasSetTextAction()
        ).assertCountEquals(1)
    }

    @Test
    fun createGroup_topBarTitleIsDisplayed() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateGroup(nav = navController)
        }

        // Verify the top bar has "Crear Grupo" text
        // This will match both the title and button, so we verify there are 2
        composeTestRule.onAllNodesWithText("Crear Grupo").assertCountEquals(2)
    }
}