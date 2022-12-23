///////////////////////////////////////////////////////////////////////////////////////////////
// Octagon Plugin Person: Person plugin for Octagon application.
// Copyright (C) 2021-2022 the original author or authors.
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2
// of the License only.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.octagon.plugin.person.classes;

import org.nanoboot.powerframework.time.moment.LocalDate;
import org.nanoboot.octagon.entity.core.Entity;
import org.nanoboot.octagon.entity.core.EntityAttribute;
import org.nanoboot.octagon.entity.core.EntityAttributeType;
import lombok.Data;
import lombok.ToString;

import java.util.*;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
@ToString
@Data
public class Licence implements Entity {
    /**
     * UUID identification of this entity.
     */
    private UUID id;
    private String name;
    private LocalDate since;
    private String note;

    public void validate() {

    }

    @Override
    public void loadFromMap(Map<String, String> map) {
        setName(getStringParam("name", map));
        setSince(getLocalDateParam("since", map));
        setNote(getStringParam("note", map));
    }

    @Override
    public Class getEntityClass() {
        return getClass();
    }

    @Override
    public String[] toStringArray() {
        return new String[]{
                id == null ? "" : id.toString(),
                name == null ? "" : name,
                since == null ? "" : since.toString(),
                note == null ? "" : note
        };
    }

    @Override
    public List<EntityAttribute> getSchema() {
        List<EntityAttribute> list = new ArrayList<>();

        list.add(EntityAttribute.getIdEntityAttribute());
        list.add(new EntityAttribute("name").withMandatory(true));
        list.add(new EntityAttribute("since", EntityAttributeType.LOCAL_DATE));
        list.add(new EntityAttribute("note"));

        return list;
    }
}
