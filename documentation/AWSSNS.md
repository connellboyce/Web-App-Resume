# Amazon Web Services SNS

### Table of Contents
1. Setting up SNS in the AWS Console
2. Incorporating SNS into a Project

### Setting up SNS in the AWS Console
- Get started with SNS through the [Amazon Console](https://aws.amazon.com/sns).
![image](../src/main/resources/static/image/get-started.png)

- Begin the creation of the notification topic by naming it.
![image](../src/main/resources/static/image/name-topic.png)

- Fill in other fields and officially create the topic.
![image](../src/main/resources/static/image/create-topic.png)

- Once the topic is set up, subscriptions are possible. There is a choice of several notification protocol such as e-mail and SMS. Be sure to select the correct ARN for the topic that was just created.
![image](../src/main/resources/static/image/create-subscription.png)

- With this, SNS is set up and functional. It is now able to be connected to an application or service.


### Incorporating SNS into a Project
- Firstly, several identifiers must be collected: the topic ARN and the AWS account's access and security keys.
- Collecting the ARN is as simple as visiting your topic and copying the ARN.
![image](../src/main/resources/static/image/find-arn.png)
- Next navigate to your account on the nav-bar and select "My Security Credentials".
![image](../src/main/resources/static/image/security-credentials.png)
- Once in the credentials page, generate new access credentials. DO NOT share this with anyone as it allows full access to the associated AWS account. Collect both the access key and security key from this location.
![image](../src/main/resources/static/image/create-key.png)
- With the ARN, security key, and access key, it is now possible to access SNS through an app. This will be demonstrated with Spring Boot and the following dependency:
  - Note: As of 1/14/2020 there are compatibility issues between newer versions of Spring Boot and this dependency. For this reason, it may be necessary to use older versions of Spring Boot.
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-aws-messaging</artifactId>
</dependency>
```
- The first class needed is the SNS Client:
```java
@Configuration
public class SNSConfiguration {

    @Value("${YOUR_ACCESS_KEY}")
    private String accessKey;

    @Value("${YOUR_SECRET_KEY}")
    private String secretKey;

    @Bean(name = "amazonSNS", destroyMethod = "shutdown")
    public AmazonSNSAsync amazonSNSAsync() {
        return AmazonSNSAsyncClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey)))
            .withRegion(Regions.{YOUR_AWS_REGION})
            .build();
    }
}
```

- The next class needed is the controller for the SNS client:
```java
@RestController
public class SNSController {
    @Autowired
    private AmazonSNSAsync amazonSNSClient;

    String TOPIC_ARN = ${YOUR_ARN_HERE};

    @GetMapping("/subscribe/{email}")
    public String addSubscription(@PathVariable String email) {
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
        amazonSNSClient.subscribe(request);
        return "Subscription pending. Please wait for e-mail confirmation. This might take a few minutes.";
    }

    @GetMapping("/sendEmail")
    public String publishMessage() {
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN,buildEmailBody(),"Notification: Amazon SNS");
        amazonSNSClient.publish(publishRequest);
        return "Notification published.";
    }

    private String buildEmailBody() {
        return "This is the sample email response.";
    }
}
```
- After this, the app will operate through API endpoints to communicate with the SNS console.