import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.s3.S3Client;

@SpringBootTest(classes = S3Config.class)
class S3ConfigTest {

    @Autowired
    private S3Client s3Client;

    @Test
    void testS3ClientBean() {
        // Assert
        assertNotNull(s3Client);
    }

    // Add more test cases for additional scenarios if needed
}
