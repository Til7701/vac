package de.holube.vac.stream;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public final class Downstream<I, O> {

    private boolean reuseInput = true;

    @Setter(AccessLevel.PACKAGE)
    private I[][] input;

    private O[][] output;

    @SuppressWarnings("unchecked")
    public O[][] createOutputWithSameDimensions(O defaultElement) {
        output = (O[][]) new Object[input.length][];
        for (int i = 0; i < input.length; i++) {
            output[i] = (O[]) new Object[input[i].length];
            for (int j = 0; j < input[i].length; j++) {
                output[i][j] = defaultElement;
            }
        }
        return output;
    }

}
