class EmployeeDetailsPage extends React.Component {
	  constructor(props) {
	    super(props);

	    this.state = {
	    	employee:{
	    		active:false,
	    		firstName: "",
	    		lastName: "",
	    		photoUrl: "",
	    		title:{},
	    		contacts:[],
	    		knownTechnologies: []
	    	},
	    	error:""
		};
	  }

	  componentDidMount() {
		  var that = this;
		  if(!this.props.match.params.id){
			  this.setState((prevState, props) => ({
		    		error:"Id is not present"
    		  }));
			  return;
		  }
		  
		  $.ajax({
		        url: "/v1/team-members/"+that.props.match.params.id,
		    })
		    .done(function(data) {
		    	that.setState((prevState, props) => ({
		    		employee: data
	    		}));
		    })
		    .fail(function() {
		    	if ( console && console.log ) {
		  	      console.log("Service call failed");
		  	    }
		    	
		    	that.setState((prevState, props) => ({
		    		error:"Failed to retrieve employee"
	    		}));
		    });
	  }

	  componentWillUnmount() {
	    
	  }
	  	
	  render() {
		if(!isEmpty(this.state.error)){
			return (
				<div id="error" class="alert alert-warning" role="alert">
					{this.state.error}
				</div>
			);
		}
		  
		let contacts = [];
		for (var i = 0; i < this.state.employee.contacts.length; i++) {
			contacts.push(
				<Contact key={this.state.employee.contacts[i].type.id} contact={this.state.employee.contacts[i]}/>
			);
		}

	    return (
	    	<div id="employee-page">
		    	<div id="employee" class="list-group list-group-flush text-left">
			    	<div class="list-group-item flex-column align-items-start">
			    		<img class="img-thumbnail" src={this.state.employee.photoUrl} alt={this.state.employee.firstName+" "+this.state.employee.lastName+" photo"}/>
			    		<h2>{this.state.employee.firstName+" "+this.state.employee.lastName}</h2>
			    		<p>{this.state.employee.title.description}</p>
			    	</div>
			    	<div class="list-group-item flex-column align-items-start">
			    		{contacts}
			    	</div>
			    </div>
			</div>
	    );
	  }
}



