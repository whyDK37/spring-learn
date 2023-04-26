package shell;

import org.springframework.shell.component.*;
import org.springframework.shell.component.support.SelectorItem;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
public class ComponentCommands extends AbstractShellComponent {

    @ShellMethod(key = "component string", value = "String input", group = "Components")
    public String stringInput(boolean mask) {
        StringInput component = new StringInput(getTerminal(), "Enter value", "myvalue");
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        if (mask) {
            component.setMaskCharacter('*');
        }
        StringInput.StringInputContext context = component.run(StringInput.StringInputContext.empty());
        return "Got value " + context.getResultValue();
    }

    @ShellMethod(key = "component path input", value = "Path input", group = "Components")
    public String pathInput() {
        PathInput component = new PathInput(getTerminal(), "Enter value");
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        PathInput.PathInputContext context = component.run(PathInput.PathInputContext.empty());
        return "Got value " + context.getResultValue();
    }

    @ShellMethod(key = "component confirmation", value = "Confirmation input", group = "Components")
    public String confirmationInput(boolean no) {
        ConfirmationInput component = new ConfirmationInput(getTerminal(), "Enter value", !no);
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        ConfirmationInput.ConfirmationInputContext context = component.run(ConfirmationInput.ConfirmationInputContext.empty());
        return "Got value " + context.getResultValue();
    }

    @ShellMethod(key = "component single", value = "Single selector", group = "Components")
    public String singleSelector() {
        SelectorItem<String> i1 = SelectorItem.of("key1", "value1");
        SelectorItem<String> i2 = SelectorItem.of("key2", "value2");
        List<SelectorItem<String>> items = Arrays.asList(i1, i2);
        SingleItemSelector<String, SelectorItem<String>> component = new SingleItemSelector<>(getTerminal(),
                items, "testSimple", null);
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        SingleItemSelector.SingleItemSelectorContext<String, SelectorItem<String>> context = component
                .run(SingleItemSelector.SingleItemSelectorContext.empty());
        String result = context.getResultItem().flatMap(si -> Optional.ofNullable(si.getItem())).get();
        return "Got value " + result;
    }

    @ShellMethod(key = "component multi", value = "Multi selector", group = "Components")
    public String multiSelector() {
        List<SelectorItem<String>> items = new ArrayList<>();
        items.add(SelectorItem.of("key1", "value1"));
        items.add(SelectorItem.of("key2", "value2", false, true));
        items.add(SelectorItem.of("key3", "value3"));
        MultiItemSelector<String, SelectorItem<String>> component = new MultiItemSelector<>(getTerminal(),
                items, "testSimple", null);
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        MultiItemSelector.MultiItemSelectorContext<String, SelectorItem<String>> context = component
                .run(MultiItemSelector.MultiItemSelectorContext.empty());
        String result = context.getResultItems().stream()
                .map(si -> si.getItem())
                .collect(Collectors.joining(","));
        return "Got value " + result;
    }

}