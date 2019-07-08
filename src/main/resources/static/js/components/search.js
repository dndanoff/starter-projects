class Search extends React.Component {
	  constructor(props) {
	    super(props);
	    this.handleChange = this.handleChange.bind(this);
	    this.handleClick = this.handleClick.bind(this);
	    
	    this.state = {
	    	searchInput: ""
	    }
	  }

	  handleChange(e) {
		let newValue = e.target.value;
		this.setState((prevState, props) => ({
			searchInput: newValue
  		}));
	    this.props.onSearch(newValue);
	  }
	  
	  handleClick(){
		  this.setState((prevState, props) => ({
				searchInput: ""
		  }));
		  this.props.onSearch("");
	  }


	  render() {
		const input = this.state.searchInput;
	    return (
    		<div class="input-group mb-3">
    		  <input type="text" value={input}  onChange={this.handleChange} class="form-control" placeholder="employee name" aria-label="employee name" aria-describedby="basic-addon2" />
    		  <div class="input-group-append">
    		    <button class="btn btn-outline-secondary" type="button" onClick={this.handleClick}>X</button>
    		  </div>
    		</div>
	    );
	  }
}