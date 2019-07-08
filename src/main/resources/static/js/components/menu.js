var NavLink = ReactRouterDOM.NavLink;

class Menu extends React.Component {
	  constructor(props) {
	    super(props);
	    this.state = {};
	  }

	  render() {
	    return (
	    	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
				<NavLink className="navbar-brand" to="/">Dreamix</NavLink>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#menu" aria-controls="menu" aria-expanded="false"
					aria-label="Toggle menu">
					<span class="navbar-toggler-icon"></span>
				</button>
				
				<div class="collapse navbar-collapse" id="menu">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item"><NavLink className="nav-link" to="/employees">Team </NavLink></li>
					</ul>
				</div>
			</nav>
	    );
	  }
}