class LandingPage extends React.Component {
	  constructor(props) {
	    super(props);
	    this.state = {};
	  }

	  render() {
	    return (
	  		<div class="container text-center">
	  			<NavLink className="nav-link" to="/employees"><img id="logo" class="img-fluid" src="/images/dreamix-logo.png"/></NavLink>
			</div>
	    );
	  }
}