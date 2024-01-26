const Base=({title="Welcome to Blog",children})=>{
    return  (
        <div classname="container-fluid">

            <h1>this is header</h1>

            {children}

            <h1>this is footer</h1>
        </div>
    );
};

export default Base;