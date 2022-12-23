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
import org.nanoboot.octagon.core.exceptions.OctagonException;
import org.nanoboot.octagon.entity.core.Entity;
import org.nanoboot.octagon.entity.core.EntityAttribute;
import org.nanoboot.octagon.entity.core.EntityAttributeType;
import lombok.Data;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
@ToString
@Data
public class ReadingDiary implements Entity {
    /**
     * UUID identification of this entity.
     */
    private UUID id;

    private String name;
    private Integer pageCount;
    private Integer pagesRead;

    private LocalDate since;
    private LocalDate until;

    private Boolean inProgress;
    private UUID thingId;
    private ReadingDiaryType readingDiaryType;

    public void validate() {
        if (pageCount == null || pagesRead == null || readingDiaryType == null || inProgress == null) {
            throw new OctagonException("Invalid ReadingDiary content:" + this.toString());
        }
        if (pageCount< 0 || pagesRead< 0) {
                throw new OctagonException("Invalid ReadingDiary content:" + this.toString());
        }
        if (pagesRead > pageCount) {
            throw new OctagonException("read pages cannot be larger than page count.");
        }
    }

    @Override
    public void loadFromMap(Map<String, String> map) {
        setName(getStringParam("name", map));
        setPageCount(getIntegerParam("pageCount", map));
        setPagesRead(getIntegerParam("pagesRead", map));

        setSince(getLocalDateParam("since", map));
        setUntil(getLocalDateParam("until", map));

        setInProgress(getBooleanParam("inProgress", map));
        setThingId(getUuidParam("thingId", map));

        String readingDiaryTypeFromRequest = getStringParam("readingDiaryType", map);
        setReadingDiaryType(readingDiaryTypeFromRequest == null ? null : ReadingDiaryType.valueOf(readingDiaryTypeFromRequest));
    }

    @Override
    public Class getEntityClass() {
        return getClass();
    }

    @Override
    public String[] toStringArray() {
        return new String[]{
                id== null ? "" : id.toString(),

                name == null ? "" : name,
                pageCount == null ? "" : pageCount.toString(),
                pagesRead == null ? "" : pagesRead.toString(),

                since == null ? "" : since.toString(),
                until == null ? "" : until.toString(),

                inProgress == null ? "" : convertBooleanToString(inProgress),
                thingId == null ? "" : thingId.toString(),
                readingDiaryType == null ? "" : readingDiaryType.name(),
        };
    }

    @Override
    public List<EntityAttribute> getSchema() {
        List<EntityAttribute> list = new ArrayList<>();

        list.add(EntityAttribute.getIdEntityAttribute());

        list.add(new EntityAttribute("name").withMandatory(true));
        list.add(new EntityAttribute("pageCount", EntityAttributeType.INTEGER).withMandatory(true));
        list.add(new EntityAttribute("pagesRead", EntityAttributeType.INTEGER).withMandatory(true).withDefaultValue("0"));

        list.add(new EntityAttribute("since", EntityAttributeType.LOCAL_DATE));
        list.add(new EntityAttribute("until", EntityAttributeType.LOCAL_DATE));

        list.add(new EntityAttribute("inProgress", EntityAttributeType.BOOLEAN).withMandatory(true));
        list.add(new EntityAttribute("thingId", "thing", "getBookThings"));
        list.add(new EntityAttribute(
                "readingDiaryType",
                Arrays.stream(ReadingDiaryType.values()).map(ReadingDiaryType::name).collect(Collectors.toList()))
                .withMandatory(true)
                .withDefaultValue(ReadingDiaryType.PAPER.name()));

        return list;
    }
}
