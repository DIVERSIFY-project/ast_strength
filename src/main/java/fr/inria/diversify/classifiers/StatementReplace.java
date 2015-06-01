package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;

/**
 * Gives a little high weight to all delete transformations
 * <p/>
 * Created by marodrig on 13/10/2014.
 */
public class StatementReplace extends TransformClassifier {

    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        MethodCallReplace method = new MethodCallReplace();
        FieldAssignmentReplace field = new FieldAssignmentReplace();
        ReturnReplace ret = new ReturnReplace();
        BlockReplace block = new BlockReplace();

        return transform.getType().contains("replace") && ((block.value(transform) +
                method.value(transform) + field.value(transform) + ret.value(transform)) == 0);
    }

    @Override
    protected int calculateValue(Transformation transform) {
        return getWeight();
    }

    @Override
    public String getDescription() {
        return "Other statements replaced";
    }

    @Override
    public int getWeight() {
        return MEDIUM;
    }
}
