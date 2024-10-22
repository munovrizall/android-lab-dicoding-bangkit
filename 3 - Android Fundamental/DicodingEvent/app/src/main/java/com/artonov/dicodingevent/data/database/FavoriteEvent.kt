import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_event")
data class FavoriteEvent(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "summary")
    var summary: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "link")
    var link: String = "",

    @ColumnInfo(name = "owner_name")
    var ownerName: String = "",

    @ColumnInfo(name = "city_name")
    var cityName: String = "",

    @ColumnInfo(name = "registrants")
    var registrants: Int? = null,

    @ColumnInfo(name = "quota")
    var quota: Int? = null,

    @ColumnInfo(name = "begin_time")
    var beginTime: String = "",

    @ColumnInfo(name = "end_time")
    var endTime: String = "",

    @ColumnInfo(name = "category")
    var category: String = "",

    @ColumnInfo(name = "media_cover")
    var mediaCover: String? = null,

    @ColumnInfo(name = "image_logo")
    var imageLogo: String? = null
) : Parcelable
