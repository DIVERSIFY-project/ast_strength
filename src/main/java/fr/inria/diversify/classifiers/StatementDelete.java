package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;

/**
 * Gives a little high weight to all delete transformations
 * <p/>
 * Created by marodrig on 13/10/2014.
 */
public class StatementDelete extends TransformClassifier {

    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        MethodCallDelete method = new MethodCallDelete();
        FieldAssignmentDelete field = new FieldAssignmentDelete();
        ReturnDelete ret = new ReturnDelete();
        BlockDelete block = new BlockDelete();

        return transform.getType().contains("delete") && ((block.value(transform) +
                method.value(transform) + field.value(transform) + ret.value(transform)) == 0);
    }

    @Override
    protected int calculateValue(Transformation transform) {
        return getWeight();
    }

    @Override
    public String getDescription() {
        return "Other statements deleted";
    }

    @Override
    public int getWeight() {
        return MEDIUM;
    }
}
