package swistdaw.learn.java.world.FPbyVenkat;

import java.util.function.Supplier;

public class Ch004DesignAndLabda {


}

class Mailer {
    private String fromAddress;
    private String toAddress;
    private String subjet;
    private String msg;

    public void from(final String address) {
        this.fromAddress = address;
    }

    public void to(final String address) {
        this.toAddress = address;
    }

    public void subject(final String line) {
        this.subjet = line;
    }

    public void body(final String message) {
        this.msg = message;
    }

    public void send() {
        System.out.println("sending...");
    }
}


class Heavy {
    public Heavy() {
        System.out.println("Heavy created");
    }

    public String toString() {
        return "quite heavy";
    }
}


class Holder {
    private Supplier<Heavy> heavy = () -> createAndCacheHeavy();

    public Holder() {
        System.out.println("Holder created");
    }

    public Heavy getHeavy() {

        return heavy.get();
    }

    private synchronized Heavy createAndCacheHeavy() {
        class HeavyFactory implements Supplier<Heavy> {
            private final Heavy heavyInstance = new Heavy();

            public Heavy get() {
                return heavyInstance;
            }
        }
        if (!HeavyFactory.class.isInstance(heavy)) {
            heavy = new HeavyFactory();
        }
        return heavy.get();
    }
}