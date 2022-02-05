package main.java.model;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class BudgetProgressElement {
    // Progressbar
    // Label: Category
    // Label: Budget
    // Label: Prozent

    ProgressBar progress = new ProgressBar();

    Label category = new Label();

    Label budget = new Label();

    Label percent = new Label();

    public BudgetProgressElement(String category, double budget, double usedBudget, int offset){
        if (usedBudget < 0){
            usedBudget *= (-1);
        }
        if (usedBudget == 0 && budget == 0){
            budget = 1;
        }
        if (budget == 0) {
            budget = usedBudget;
        }

        double progressedBudget = usedBudget / budget;
        this.category.setText(category);
        this.budget.setText(usedBudget+ " / " + budget);
        this.percent.setText((int) progressedBudget*100 + " % used.");

        this.progress.setProgress(progressedBudget);
        if (progressedBudget > 0.8){
            this.progress.setStyle("-fx-accent: red;");
        }
        setLayout(offset);

    }

    public void setLayout(int offsetCounter){
        double offsetX = 0.0;
        if (offsetCounter / 18 >= 1 ){
            offsetX +=  500 * (int)(offsetCounter / 18);
        }
        double offset = 45.0 * (offsetCounter % 18);
        this.progress.setLayoutX(50.0 + offsetX);
        this.progress.setLayoutY(150.0 + offset);
        this.progress.setPrefWidth(250.0);

        this.category.setLayoutX(50.0 + offsetX);
        this.category.setLayoutY(130.0 + offset);

        this.budget.setLayoutX(340.0 + offsetX);
        this.budget.setLayoutY(150.0 + offset);

        this.percent.setLayoutX(460.0 + offsetX);
        this.percent.setLayoutY(150.0 + offset);
    }

    public void addToPane(Pane pane){
        pane.getChildren().addAll(this.progress, this.budget, this.category, this.percent);
    }

}
