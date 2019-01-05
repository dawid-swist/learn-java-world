package swistdaw.learn.java.world.FPbyVenkat;

import org.junit.Test;
import static org.junit.Assert.*;

public class Ch00LambdaTest {

     @FunctionalInterface
    interface FP_Int_inc { int increment(int x);}

    @Test
    public void shouldCreateLambdaFunction() {

        FP_Int_inc lambdaIncExp = x -> x + 1;
        assertEquals(2, lambdaIncExp.increment(1));
    }
}
}