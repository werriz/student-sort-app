package com.jurijz.studentsortapp.client;

import com.jurijz.studentsortapp.client.components.ErrorNotification;
import com.jurijz.studentsortapp.domain.Student;
import com.jurijz.studentsortapp.service.input.InputFile;
import com.jurijz.studentsortapp.service.sorting.StudentSorting;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by jurijz on 10/13/2018.
 */
@Route
@Theme(Lumo.class)
public class MainView extends VerticalLayout {

    private final static Logger LOG = LoggerFactory.getLogger(MainView.class);

    private final List<StudentSorting> sortingMethodList;
    private final InputFile inputFile;

    private ComboBox<StudentSorting> sortingMethodsComboBox;
    private final Upload upload;
    private final Grid<Student> unsortedGrid;
    private final Grid<Student> sortedGrid;
    private final HorizontalLayout gridsLayout = new HorizontalLayout();


    public MainView(List<StudentSorting> sortingMethodList, InputFile inputFile) {
        this.sortingMethodList = sortingMethodList;
        this.inputFile = inputFile;

        unsortedGrid = createStudentList();
        sortedGrid = createStudentList();
        upload = createFileUpload();
        VerticalLayout panel = new VerticalLayout();
        panel.setSizeFull();

        panel.add(createPageHeader());
        panel.add(createBody());

        add(panel);
    }

    @SuppressWarnings("unchecked")
    private Component createBody() {
        VerticalLayout bodyLayout = new VerticalLayout();

        sortingMethodsComboBox = new ComboBox<>();
        sortingMethodsComboBox.setPlaceholder("Choose sorting method...");
        sortingMethodsComboBox.setWidth("310px");
        sortingMethodsComboBox.setDataProvider(new ListDataProvider<>(sortingMethodList));
        sortingMethodsComboBox.setRequired(true);
        sortingMethodsComboBox.addValueChangeListener(event -> {

            final boolean selectionIsNotNull = event.getValue() != null;
            upload.setVisible(selectionIsNotNull);

            if (selectionIsNotNull && gridsLayout.isVisible()) {

                gridsLayout.setVisible(true);
                Collection<Student> unsortedStudents =
                        ((ListDataProvider<Student>) unsortedGrid.getDataProvider())
                                .getItems();
                if (unsortedStudents != null && unsortedStudents.isEmpty()) {
                    sort(new ArrayList<>(unsortedStudents));
                }
            }
        });

        gridsLayout.add(new VerticalLayout(new Label("Unsorted"), unsortedGrid),
                new VerticalLayout(new Label("Sorted"), sortedGrid));
        gridsLayout.setHeight("400px");
        gridsLayout.setWidth("50%");
        gridsLayout.setVisible(false);

        bodyLayout.add(new HorizontalLayout(sortingMethodsComboBox, upload));
        bodyLayout.add(gridsLayout);
        bodyLayout.setSizeFull();
        return bodyLayout;
    }


    private Component createPageHeader() {
        HorizontalLayout headerLayout = new HorizontalLayout(new H2("Students"));
        headerLayout.setWidth("100%");
        headerLayout.setMargin(true);
        headerLayout.getStyle().set("border-bottom", "1px solid grey");
        return headerLayout;
    }

    private Upload createFileUpload() {
        MemoryBuffer receiver = new MemoryBuffer();
        Upload upload = new Upload();
        upload.setReceiver(receiver);
        upload.setVisible(false);
        upload.setDropAllowed(false);
        upload.setWidth("310px");

        upload.addSucceededListener(event -> {
            try {
                gridsLayout.setVisible(true);
                List<Student> initialStudents =
                        inputFile.readStream(receiver.getInputStream());
                sort(initialStudents);

            } catch (IOException e) {
                LOG.error(e.getMessage());
                new ErrorNotification(e.getMessage());
                //TODO: Vaadin doesn't provide convenient way to remove files programmatically.

                gridsLayout.setVisible(false);
            }
        });

        return upload;
    }

    private void sort(List<Student> unsortedStudents) {
        unsortedGrid.setDataProvider(new ListDataProvider<>(unsortedStudents));

        Date startTime = new Date();
        List<Student> sortedStudents = sortingMethodsComboBox.getValue().sort(unsortedStudents);
        Date endTime = new Date();
        sortedGrid.setDataProvider(new ListDataProvider<>(sortedStudents));
        sortedGrid.getColumnByKey("nameColumn")
                .setFooter("Sort time:");
        sortedGrid.getColumnByKey("performanceColumn")
                .setFooter((endTime.getTime() - startTime.getTime()) + "ms.");
    }

    private Grid<Student> createStudentList() {
        Grid<Student> grid = new Grid<>();
        grid.addColumn(Student::getName).setKey("nameColumn")
                .setHeader("Student name");
        grid.addColumn(Student::getPerformance).setKey("performanceColumn")
                .setHeader("Performance");
        grid.setSizeFull();

        return grid;
    }
}