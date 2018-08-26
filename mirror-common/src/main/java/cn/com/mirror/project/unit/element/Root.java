package cn.com.mirror.project.unit.element;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author piggy
 * @description
 * @date 18-8-24
 */
@Getter
@Setter
public class Root extends Base {

    public Root(String targetPath,
                Integer startLineNum,
                Integer endLineNum,
                String content,
                String packageName) {

        super(targetPath, startLineNum, endLineNum, content, packageName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Root root = (Root) o;
        return Objects.equals(this.getPackageName(), root.getPackageName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPackageName());
    }
}
