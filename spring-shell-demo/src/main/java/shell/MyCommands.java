package shell;

import org.springframework.shell.Availability;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.completion.CompletionResolver;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.standard.ValueProvider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class MyCommands {
    static class MyValuesProvider implements ValueProvider {

        @Override
        public List<CompletionProposal> complete(CompletionContext completionContext) {
            return Arrays.asList("val1", "val2").stream()
                    .map(CompletionProposal::new)
                    .collect(Collectors.toList());
        }
    }

    @ShellMethod(value = "complete", key = "complete")
    public String complete(
            @ShellOption(valueProvider = MyValuesProvider.class) String arg1)
    {
        return "You said " + arg1;
    }

    @ShellMethod(key = "hello-world", group = "why",interactionMode = InteractionMode.INTERACTIVE)
    public String helloWorld(@ShellOption(defaultValue = "spring") String arg) {
        return "Hello world " + arg;
    }

    private boolean connected;

    @ShellMethod("Connect to the server.")
    public void connect(String user, String password) {
        connected = true;
    }

    @ShellMethod("Download the nuclear codes.")
    public void download() {

    }

    public Availability downloadAvailability() {
        return connected
                ? Availability.available()
                : Availability.unavailable("you are not connected");
    }
}