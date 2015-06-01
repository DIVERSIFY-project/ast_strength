package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;

import java.util.List;

/**
 * Created by marodrig on 01/06/2015.
 */
public class ClassifiersIT {

    List<TransformClassifier> classifierList = null;

    /**
     * Classifies a transformation
     * @param t Transformation to classify
     * @return A float with the transformation strength
     */
    public float classify(Transformation t) {

        float result = 0f;

        if ( classifierList == null ) {
            ClassifierFactory factory = new ClassifierFactory();
            classifierList = factory.buildClassifiers();
        }

        for ( TransformClassifier tc : classifierList ) {
            result += tc.value(t);
        }
        return result;
    }

}
