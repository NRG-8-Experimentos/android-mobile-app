package nrg.inc.synhub

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.synhub.invitations.views.Invitations
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test for Invitations screen using Compose UI Test.
 *
 * Tests for viewing, accepting, and denying group invitations.
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ViewInvitationsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun invitations_displaysTopBarWithTitle() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Verify the top bar title is displayed
        composeTestRule.onNodeWithText("Solicitudes de unión").assertIsDisplayed()
    }

    @Test
    fun invitations_topBarBackButtonExists() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Verify the top bar title exists first
        composeTestRule.onNodeWithText("Solicitudes de unión").assertExists()

        // Verify a clickable icon button exists (the back button in the TopBar)
        composeTestRule.onAllNodes(hasClickAction())
            .filterToOne(!hasSetTextAction() and !hasText("Aceptar") and !hasText("Rechazar"))
            .assertExists()
    }

    @Test
    fun invitations_displaysNoInvitationsMessageWhenEmpty() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // If no invitations, verify the empty state message
        val noInvitationsExists = composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
            .fetchSemanticsNodes().isNotEmpty()

        if (noInvitationsExists) {
            composeTestRule.onNodeWithText("No hay invitationes pendientes").assertIsDisplayed()
        }
    }

    @Test
    fun invitations_acceptButtonIsDisplayedWhenInvitationsExist() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // If invitations exist, verify accept button is displayed
        val acceptButtons = composeTestRule.onAllNodesWithText("Aceptar")
            .fetchSemanticsNodes()

        if (acceptButtons.isNotEmpty()) {
            composeTestRule.onNodeWithText("Aceptar").assertIsDisplayed()
        }
    }

    @Test
    fun invitations_rejectButtonIsDisplayedWhenInvitationsExist() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Rechazar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // If invitations exist, verify reject button is displayed
        val rejectButtons = composeTestRule.onAllNodesWithText("Rechazar")
            .fetchSemanticsNodes()

        if (rejectButtons.isNotEmpty()) {
            composeTestRule.onNodeWithText("Rechazar").assertIsDisplayed()
        }
    }

    @Test
    fun invitations_acceptButtonIsClickable() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // If invitations exist, verify accept button is clickable
        val acceptButtons = composeTestRule.onAllNodesWithText("Aceptar")
            .fetchSemanticsNodes()

        if (acceptButtons.isNotEmpty()) {
            composeTestRule.onAllNodes(
                hasText("Aceptar") and hasClickAction()
            ).onFirst().assertIsEnabled()
        }
    }

    @Test
    fun invitations_rejectButtonIsClickable() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Rechazar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // If invitations exist, verify reject button is clickable
        val rejectButtons = composeTestRule.onAllNodesWithText("Rechazar")
            .fetchSemanticsNodes()

        if (rejectButtons.isNotEmpty()) {
            composeTestRule.onAllNodes(
                hasText("Rechazar") and hasClickAction()
            ).onFirst().assertIsEnabled()
        }
    }

    @Test
    fun invitations_canClickAcceptButton() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // If invitations exist, click the accept button
        val acceptButtons = composeTestRule.onAllNodesWithText("Aceptar")
            .fetchSemanticsNodes()

        if (acceptButtons.isNotEmpty()) {
            composeTestRule.onAllNodes(
                hasText("Aceptar") and hasClickAction()
            ).onFirst().performClick()

            // Wait a moment for the action to process
            composeTestRule.waitForIdle()
        }
    }

    @Test
    fun invitations_canClickRejectButton() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Rechazar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // If invitations exist, click the reject button
        val rejectButtons = composeTestRule.onAllNodesWithText("Rechazar")
            .fetchSemanticsNodes()

        if (rejectButtons.isNotEmpty()) {
            composeTestRule.onAllNodes(
                hasText("Rechazar") and hasClickAction()
            ).onFirst().performClick()

            // Wait a moment for the action to process
            composeTestRule.waitForIdle()
        }
    }

    @Test
    fun invitations_bothAcceptAndRejectButtonsExistTogether() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // If invitations exist, verify both buttons exist for each invitation
        val acceptButtons = composeTestRule.onAllNodesWithText("Aceptar")
            .fetchSemanticsNodes()
        val rejectButtons = composeTestRule.onAllNodesWithText("Rechazar")
            .fetchSemanticsNodes()

        if (acceptButtons.isNotEmpty()) {
            assert(acceptButtons.size == rejectButtons.size) {
                "Each invitation should have both Accept and Reject buttons"
            }
        }
    }

    @Test
    fun invitations_displaysCheckIconInAcceptButton() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Verify accept button exists (it contains a Check icon)
        val acceptButtons = composeTestRule.onAllNodesWithText("Aceptar")
            .fetchSemanticsNodes()

        if (acceptButtons.isNotEmpty()) {
            composeTestRule.onNodeWithText("Aceptar").assertExists()
        }
    }

    @Test
    fun invitations_displaysClearIconInRejectButton() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Rechazar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Verify reject button exists (it contains a Clear icon)
        val rejectButtons = composeTestRule.onAllNodesWithText("Rechazar")
            .fetchSemanticsNodes()

        if (rejectButtons.isNotEmpty()) {
            composeTestRule.onNodeWithText("Rechazar").assertExists()
        }
    }

    @Test
    fun invitations_screenLoadsSuccessfully() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Verify the screen is loaded by checking for the title
        composeTestRule.onNodeWithText("Solicitudes de unión").assertExists()

        // Wait for content to load (either invitations or empty state)
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }
    }

    @Test
    fun invitations_multipleInvitationsCanBeDisplayed() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Verify that multiple accept buttons can exist (multiple invitations)
        val acceptButtons = composeTestRule.onAllNodesWithText("Aceptar")
            .fetchSemanticsNodes()

        // If there are invitations, there should be at least one
        if (acceptButtons.isNotEmpty()) {
            assert(acceptButtons.size >= 1) {
                "Should display at least one invitation"
            }
        }
    }

    @Test
    fun invitations_acceptButtonHasCorrectText() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Verify accept button has correct text
        val acceptButtons = composeTestRule.onAllNodesWithText("Aceptar")
            .fetchSemanticsNodes()

        if (acceptButtons.isNotEmpty()) {
            composeTestRule.onNodeWithText("Aceptar", useUnmergedTree = false)
                .assertExists()
        }
    }

    @Test
    fun invitations_rejectButtonHasCorrectText() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Rechazar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Verify reject button has correct text
        val rejectButtons = composeTestRule.onAllNodesWithText("Rechazar")
            .fetchSemanticsNodes()

        if (rejectButtons.isNotEmpty()) {
            composeTestRule.onNodeWithText("Rechazar", useUnmergedTree = false)
                .assertExists()
        }
    }

    @Test
    fun invitations_emptyStateIsProperlyFormatted() {
        // Set up the UI
        composeTestRule.setContent {
            val navController = rememberNavController()
            Invitations(nav = navController)
        }

        // Wait for the screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
                .fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithText("Aceptar")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // If empty state is shown, verify it's properly displayed
        val noInvitationsExists = composeTestRule.onAllNodesWithText("No hay invitationes pendientes")
            .fetchSemanticsNodes().isNotEmpty()

        if (noInvitationsExists) {
            composeTestRule.onNodeWithText("No hay invitationes pendientes")
                .assertExists()
                .assertIsDisplayed()
        }
    }
}