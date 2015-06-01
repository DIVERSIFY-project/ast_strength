package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;

/**
 * Gives a little high weight to all delete transformations
 * <p/>
 * Created by marodrig on 13/10/2014.
 */
public class StatementAdd extends TransformClassifier {

    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        MethodCallAdd method = new MethodCallAdd();
        FieldAssignmentAdd field = new FieldAssignmentAdd();
        ReturnAdd ret = new ReturnAdd();
        BlockAdd block = new BlockAdd();

        return transform.getType().contains("add") && ((block.value(transform) +
                method.value(transform) + field.value(transform) + ret.value(transform)) == 0);
    }

    @Override
    protected int calculateValue(Transformation transform) {
        return getWeight();
    }

    @Override
    public String getDescription() {
        return "Other statements added";
    }

    @Override
    public int getWeight() {
        return MEDIUM;
    }
}
