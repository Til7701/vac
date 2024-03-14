package de.holube.vac.collections;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pair<U, V> {

    protected U first;
    protected V second;

}
