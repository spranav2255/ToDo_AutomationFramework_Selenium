package utilities;

public class StepNameTracker {
    private static final ThreadLocal<String> currentStep = new ThreadLocal<>();

    public static void setStepName(String stepName) {
        currentStep.set(stepName);
    }

    public static String getStepName() {
        return currentStep.get();
    }

    public static void clear() {
        currentStep.remove();
    }
}
