package net.rillekk.mlgrush.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/***************************************************************
 *                                                             *
 *   @author Rillekk                                           *
 *   @Instagram: rillekk                                       *
 *   @Discord: Rillekk#8642                                    *
 *                                                             *
 *                                                             *
 *   Jede Art der Vervielfältigung, Verbreitung, Verleihung,   *
 *   öffentlich Zugänglichmachung oder andere Nutzung bedarf   *
 *   der ausdrücklichen, schriftlichen Zustimmung von Rillekk. *
 *                                                             *
 ***************************************************************/


@Getter
@Setter
@AllArgsConstructor
public class Pair<LEFT, RIGHT> {

    private LEFT left;
    private RIGHT right;

    public static <LEFT, RIGHT> Pair create(LEFT left, RIGHT right) {
        return new Pair<>(left, right);
    }

}
