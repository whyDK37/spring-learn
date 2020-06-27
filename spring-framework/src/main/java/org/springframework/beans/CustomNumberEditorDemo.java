package org.springframework.beans;

import org.springframework.beans.propertyeditors.CustomNumberEditor;

public class CustomNumberEditorDemo {

  public static void main(String[] args) {
    final CustomNumberEditor editor = new CustomNumberEditor(Integer.class, false);
    PropertyEditorRegistrySupport propertyEditorRegistry = new PropertyEditorRegistrySupport();
    propertyEditorRegistry.registerDefaultEditors();
    propertyEditorRegistry
        .registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));


    System.out.println(propertyEditorRegistry.getDefaultEditor(Integer.class));
    System.out.println(propertyEditorRegistry.getDefaultEditor(Integer.class));
    System.out.println(propertyEditorRegistry.getDefaultEditor(Integer.class));
    editor.setValue(null);
    editor.setAsText("2");
    System.out.println(editor.getValue().getClass() + "->" + editor.getValue());
  }
}
