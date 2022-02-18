import com.forthreal.ServiceConfiguration
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.keycloak.authorization.client.AuthzClient
import org.keycloak.representations.idm.authorization.AuthorizationRequest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [ServiceConfiguration::class])
@TestMethodOrder(OrderAnnotation::class)
class SimpleTests {
    @Test
    @Order(0)
    @DisplayName("Test that we can perform service account auth")
    fun serviceAccountAuthTest()
    {
        /** for this to work (i.e. for the service account to be able to have a successful authorization request),
         * you should explicitly turn on the "Authorization Enabled" flag for the service account, in the Authorization
         * tab / Resources sub-tab insert the allowed URIs and make sure that in the Authorization Scopes sub-tab there's
         * no restriction on the methods */

        val authClient = AuthzClient.create()
        val authRequest = AuthorizationRequest()
        val response = authClient.authorization().authorize(authRequest)
        val token = response.token

        Assertions.assertTrue(token != null)
    }
}