package async;

import entity.BaseEntity;
import lombok.Data;

import java.util.concurrent.CompletableFuture;
@Data
public class Transaction extends BaseEntity {
    private String id;
    private CompletableFuture<String> aFuture;
    private CompletableFuture<String> bFuture;
    private CompletableFuture<String> cFuture;
}
