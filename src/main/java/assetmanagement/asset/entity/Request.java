package assetmanagement.asset.entity;

import javax.persistence.*;
import java.util.Date;

public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staff_id", referencedColumnName = "id")
	Staff staff;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "asset_id", referencedColumnName = "id")
	Asset asset;

	@Temporal(TemporalType.TIMESTAMP)
	Date request_time;

}
