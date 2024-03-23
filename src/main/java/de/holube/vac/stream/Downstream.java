package de.holube.vac.stream;

import de.holube.vac.collections.TwoDList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public final class Downstream<I, O> {

    private boolean reuseInput = true;

    private TwoDList<O> output;

}
