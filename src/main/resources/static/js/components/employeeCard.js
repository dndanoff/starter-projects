var NavLink = ReactRouterDOM.NavLink;

class EmployeeCard extends React.Component {
	  constructor(props) {
	    super(props);
	    this.state = {};
	  }

	  render() {
	    return (
    		<div class="card">
    		<NavLink to={/employees/+this.props.info.id}><img class="card-img-top" src={this.props.info.photoUrl} alt={this.props.info.firstName+" "+this.props.info.lastName+" photo"}/></NavLink>
			  <div class="card-body">
			    <h5 class="card-title">{this.props.info.firstName+" "+this.props.info.lastName}</h5>
			    <p class="card-text">{this.props.info.title.name}</p>
			  </div>
			</div>
	    );
	  }
}