package co.com.bancolombia.dynamodb.helper;

import co.com.bancolombia.dynamodb.DynamoDBTemplateAdapter;
import co.com.bancolombia.dynamodb.MotivoContactoEntity;
import co.com.bancolombia.model.stat.Stat;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import reactor.test.StepVerifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Slf4j
class TemplateAdapterOperationsTest {

	@Mock
	private DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;

	@Mock
	private ObjectMapper mapper;

	@Mock
	private DynamoDbAsyncTable<MotivoContactoEntity> customerTable;

	private MotivoContactoEntity modelEntity;

	private Stat modelModel;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		when(dynamoDbEnhancedAsyncClient.table("MotivosContacto", TableSchema.fromBean(MotivoContactoEntity.class)))
				.thenReturn(customerTable);

		modelEntity = new MotivoContactoEntity();
		modelEntity.setTimestamp("2025-07-03T10:00:00Z");
		modelEntity.setTotalContactoClientes(250);
		modelEntity.setMotivoReclamo(25);
		modelEntity.setMotivoGarantia(10);
		modelEntity.setMotivoDuda(100);
		modelEntity.setMotivoCompra(100);
		modelEntity.setMotivoFelicitaciones(7);
		modelEntity.setMotivoCambio(8);
		modelEntity.setHash("5484062a4be1ce5645eb414663e14f59 ");

		modelModel = new Stat();
		modelModel.setTimestamp("2025-07-03T10:00:00Z");
		modelModel.setTotalContactoClientes(250);
		modelModel.setMotivoReclamo(25);
		modelModel.setMotivoGarantia(10);
		modelModel.setMotivoDuda(100);
		modelModel.setMotivoCompra(100);
		modelModel.setMotivoFelicitaciones(7);
		modelModel.setMotivoCambio(8);
		modelModel.setHash("5484062a4be1ce5645eb414663e14f59 ");
	}

	@Test
	void modelEntityPropertiesMustNotBeNull() {
		/*
		MotivoContactoEntity modelEntityUnderTest = new MotivoContactoEntity("2025-07-03T10:00:00Z", 250, 25, 10, 100,
				100, 7, 8, "5484062a4be1ce5645eb414663e14f59");
		assertNotNull(modelEntityUnderTest.getTimestamp());
		assertNotNull(modelEntityUnderTest.getHash());
		*/
	}

	@Test
	void testSave() {
		when(customerTable.putItem(modelEntity)).thenReturn(CompletableFuture.runAsync(() -> {
		}));
		when(mapper.map(modelEntity, MotivoContactoEntity.class)).thenReturn(modelEntity);

		DynamoDBTemplateAdapter dynamoDBTemplateAdapter = new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient,
				mapper);

		StepVerifier.create(dynamoDBTemplateAdapter.save(modelModel)).expectNextCount(1).verifyComplete();
	}

	@Test
	void testGetById() {
		String id = "2025-07-03T10:00:00Z";
		when(customerTable.getItem(Key.builder().partitionValue(AttributeValue.builder().s(id).build()).build()))
				.thenReturn(CompletableFuture.completedFuture(modelEntity));
		when(mapper.map(modelEntity, Stat.class)).thenReturn(modelModel);
		DynamoDBTemplateAdapter dynamoDBTemplateAdapter = new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient,
				mapper);
		StepVerifier.create(dynamoDBTemplateAdapter.getById("2025-07-03T10:00:00Z")).expectNext(modelModel)
				.verifyComplete();
	}

	@Test
	void testDelete() {
		when(mapper.map(modelEntity, MotivoContactoEntity.class)).thenReturn(modelEntity);
		when(mapper.map(modelEntity, Stat.class)).thenReturn(modelModel);

		when(customerTable.deleteItem(modelEntity)).thenReturn(CompletableFuture.completedFuture(modelEntity));

		DynamoDBTemplateAdapter dynamoDBTemplateAdapter = new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient,
				mapper);

		StepVerifier.create(dynamoDBTemplateAdapter.delete(modelModel)).expectNext(modelModel).verifyComplete();
	}
}