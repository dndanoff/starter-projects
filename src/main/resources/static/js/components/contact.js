class Contact extends React.Component {
	  constructor(props) {
	    super(props);
	    this.state = {};
	  }

	  render() {
		  let value = this.props.contact.value;
		  let contactValue = <span>{value}</span>;
			if("E-MAIL" == this.props.contact.name.toUpperCase()){
				contactValue = <a href={"mailto:"+value}>{value}</a>;
			}else if("MOBILE" == this.props.contact.name.toUpperCase()){
				contactValue = <a href={"tel:"+value}>{value}</a>;
			}
		  
	    return (
	    	<div class="row">
		    	<div class="col">
		    		{this.props.contact.name}
		    	</div>
		    	<div class="col-10">
		    		{contactValue}
		    	</div>
	    	</div>
	    );
	  }
}