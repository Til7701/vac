package de.holube.vac.collections;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Triple<U, V, W> extends Pair<U, V> {

    protected W third;

}
